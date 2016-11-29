/**
 * Created by Alex on 29/11/2016.
 */
import com.sun.media.sound.FFT
import javax.sound.sampled.*
import org.jtransforms.*
import org.jtransforms.fft.FloatFFT_1D
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import kotlin.jvm.internal.iterator

data class AudioSample(val format:AudioFormat,val data:ByteArray){
    init {
        val floatSamples : MutableList<Float> = ArrayList<Float>()
        val spectrumFrames : ArrayList<Array<Float>> = ArrayList<Array<Float>>()

        if (format.sampleSizeInBits>8){
            val buffer=ByteBuffer.wrap(data)
            if (format.isBigEndian){
                buffer.order(ByteOrder.BIG_ENDIAN)
            }else{
                buffer.order(ByteOrder.LITTLE_ENDIAN)
            }
            for (i in data.indices step 2){
                floatSamples.add(buffer.short.toFloat())
            }
        }else{
            data.forEach { floatSamples.add(it.toFloat()) }
        }

        var frameFloatSize = 0
        if (format.sampleSizeInBits>8){
            frameFloatSize=format.frameSize
        }else{
            frameFloatSize=format.frameSize/2
        }
        val fft = FloatFFT_1D(frameFloatSize.toLong())
        for (i in floatSamples.indices step frameFloatSize){ // Unsure about this
            val res = Arrays.copyOf(floatSamples.toFloatArray(),frameFloatSize*2)
            fft.realForwardFull(res)
            spectrumFrames.add(res.toTypedArray())
        }
    }

}