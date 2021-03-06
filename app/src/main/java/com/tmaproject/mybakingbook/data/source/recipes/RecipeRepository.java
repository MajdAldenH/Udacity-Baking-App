package com.tmaproject.mybakingbook.data.source.recipes;

import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.data.source.recipes.remote.RecipesRemoteDataSource;
import io.reactivex.Single;
import java.util.List;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/23/17.
 */

public class RecipeRepository implements RecipesDataSource {

  RecipesLocalDataSource mLocalDataSource;
  RecipesRemoteDataSource mRemoteDataSource;

  public RecipeRepository(RecipesLocalDataSource mLocalDataSource,
      RecipesRemoteDataSource mRemoteDataSource) {
    this.mLocalDataSource = mLocalDataSource;
    this.mRemoteDataSource = mRemoteDataSource;
  }

  public boolean isDataUpToDate(){
    return App.get().preferencesUtils.getDataUpToDate();
  }

  @Override public Single<List<Recipe>> getRecipes() {

    if(isDataUpToDate()) {
      return mLocalDataSource.getRecipes();
    }else {
      Timber.d("Data isn't up to data. Retrieving from remote source");
      return mRemoteDataSource.getRecipes();
    }
  }

  @Override public Single<Recipe> getRecipe(int recipeId) {
    return mLocalDataSource.getRecipe(recipeId);
  }

  @Override public Single<List<Ingredient>> getRecipeIngredients(int recipeId) {
    return mLocalDataSource.getRecipeIngredients(recipeId);
  }

  @Override public Single<List<Step>> getRecipeSteps(int recipeId) {
    return mLocalDataSource.getRecipeSteps(recipeId);
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {
    mLocalDataSource.saveRecipes(recipeList);
  }
}
