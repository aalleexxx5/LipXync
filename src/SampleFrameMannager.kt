/**
 * Created by Alex on 29/11/2016.
 */
class SampleFrameMannager(var sampleframes:MutableList<SampleFrame>) {


    fun registerFrames(sampleframes: List<SampleFrame>) {
        this.sampleframes.addAll(sampleframes)
    }

    fun registerFrame(frame:SampleFrame){
        sampleframes.add(frame)
    }



}

