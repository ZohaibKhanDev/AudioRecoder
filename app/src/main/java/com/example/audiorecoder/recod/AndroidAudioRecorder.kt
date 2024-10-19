package com.example.audiorecoder.recod

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.provider.MediaStore.Audio.Media
import java.io.File
import java.io.FileOutputStream

class AndroidAudioRecorder(
    val context: Context
) : AudioRecoder {

    private var recoder: MediaRecorder? = null


    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    override fun start(outPutFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outPutFile).fd)

            prepare()
            start()

            recoder = this
        }
    }

    override fun stop() {
        recoder?.stop()
        recoder?.reset()

        recoder = null
    }
}