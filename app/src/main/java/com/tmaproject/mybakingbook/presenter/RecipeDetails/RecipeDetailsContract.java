package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.BaseContract;
import java.util.List;

/**
 * Created by TarekLMA on 6/26/17.
 * tarekkma@gmail.com
 */

public interface RecipeDetailsContract {
  interface View extends BaseContract.BaseView<Presenter> {
    void showRecipeDetails(Recipe recipe);
    void showIngredients(List<Ingredient> ingredientList);
    void showSteps(List<Step> stepList);
    void showMessage(String message);
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    void loadRecipeDetails();

    void setRecipeId(int recipeId);
  }
}
