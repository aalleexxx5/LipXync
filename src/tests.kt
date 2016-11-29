import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Created by Alex on 29/11/2016.
 */

fun main(args: Array<String>) {
    var list = listOf<Byte>(-128, -128, 0, 0, 1, 1, 0, 1)
    val buffer =ByteBuffer.wrap(list.toByteArray()).order(ByteOrder.BIG_ENDIAN)
    for (i in list.indices step 2){
        //println(list[i])
        println(buffer.getShort(i).toFloat())

    }
}