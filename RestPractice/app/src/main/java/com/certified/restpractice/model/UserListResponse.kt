/*
 * Copyright (c) 2021 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.certified.restpractice.model

import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class UserListResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "per_page") val per_page: Int,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "total_pages") val total_pages: Int,
    @field:Json(name = "data") val data: List<User>
)