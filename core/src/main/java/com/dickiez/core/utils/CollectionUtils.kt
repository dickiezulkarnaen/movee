/***
 * Author         : Dicky Zulkarnain
 * Date           : 31/10/22
 * Original File  : CollectionUtils
 ***/

package com.dickiez.core.utils

fun <T> List<T?>?.mapNotNullOrEmpty() : List<T> {
  return this?.mapNotNull { it } ?: listOf()
}