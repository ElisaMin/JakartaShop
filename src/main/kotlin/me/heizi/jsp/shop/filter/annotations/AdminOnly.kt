package me.heizi.jsp.shop.filter.annotations

import jakarta.ws.rs.NameBinding


@NameBinding
annotation class AdminOnly()

/**
 * 允许未登入状态的请求，否则转到主页。
 *
 * @constructor Create empty Not login only
 */
@NameBinding
annotation class NotLoginOnly
