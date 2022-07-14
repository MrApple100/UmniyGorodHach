package com.example.umniygorodhach.presentation.utils

import androidx.annotation.StringRes
import com.example.umniygorodhach.R

open class AppBarTab(@StringRes val title: Int)
sealed class FeedbackTab(@StringRes val name: Int) : AppBarTab(name) {
	/*object Incoming : FeedbackTab(R.string.feedback_incoming)
	object Read : FeedbackTab(R.string.feedback_read)*/
}
sealed class EventTab(@StringRes val name: Int) : AppBarTab(name) {
	/*object All : EventTab(R.string.events_all)
	object My: EventTab(R.string.events_my)*/
}
sealed class NewsTab(@StringRes val name: Int) : AppBarTab(name) {
	object News: NewsTab(R.string.news)
	object Results: NewsTab(R.string.results)
}