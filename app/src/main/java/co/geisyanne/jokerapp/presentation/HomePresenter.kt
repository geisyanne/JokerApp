package co.geisyanne.jokerapp.presentation

import android.graphics.Color
import co.geisyanne.jokerapp.data.CategoryRemoteDataSource
import co.geisyanne.jokerapp.data.ListCategoryCallback
import co.geisyanne.jokerapp.model.Category
import co.geisyanne.jokerapp.view.HomeFragment


class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val categories = response.mapIndexed { index, s ->
            val stringUp = s.substring(0, 1).uppercase() + s.substring(1)

            val alpha = if (index < 9) {
                255 - (25 * index)
            } else {
                55 + (25 * (index - 9))
            }

            val h = if (index < 9) 40.0f else 200.0f
            val hsv = floatArrayOf(h, 100.0f, 100.0f)

            Category(stringUp, Color.HSVToColor(alpha, hsv).toLong())
        }
        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}