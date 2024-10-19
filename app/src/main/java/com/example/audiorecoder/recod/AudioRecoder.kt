package com.example.audiorecoder.recod

import java.io.File

interface AudioRecoder {
    fun start(outPutFile: File)
    fun stop()
}