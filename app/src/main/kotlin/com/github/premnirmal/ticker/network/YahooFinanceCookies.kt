package com.github.premnirmal.ticker.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YahooFinanceCookies @Inject constructor() : CookieJar {

  private var _cookies = mutableMapOf<String, Cookie>()

  override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
    for (cookie in cookies) {
      _cookies[cookie.name] = cookie
    }
  }

  override fun loadForRequest(url: HttpUrl): List<Cookie> {
    return _cookies.values.toList()
  }
}
