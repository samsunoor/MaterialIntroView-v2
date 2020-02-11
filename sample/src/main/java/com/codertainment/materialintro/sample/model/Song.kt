package com.codertainment.materialintro.sample.model

import androidx.annotation.DrawableRes

data class Song(var songName: String, @DrawableRes var songArt: Int, var singerName: String)