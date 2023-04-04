package co.geisyanne.jokerapp.presentation

import co.geisyanne.jokerapp.data.JokeCallback
import co.geisyanne.jokerapp.data.JokeDayRemoteDataSource
import co.geisyanne.jokerapp.model.Joke
import co.geisyanne.jokerapp.view.JokeDayFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {

    fun findByJokeDay() {
        view.showProgress()
        dataSource.findByJokeDay(this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}