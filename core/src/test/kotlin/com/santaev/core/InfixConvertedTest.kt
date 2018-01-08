import com.santaev.core.entity.InfixNotationConverter
import com.santaev.core.entity.member.IMember
import com.santaev.core.entity.member.Operator
import com.santaev.core.util.toNumber
import org.junit.Assert
import org.junit.Test

class InfixConvertedTest {

    @Test
    fun testPlusMul() {
        val exp = ArrayList<IMember>().apply {
            add(5.toNumber())
            add(Operator.PLUS)
            add(2.toNumber())
            add(Operator.MULTIPLE)
            add(3.toNumber())
        }
        val expected = ArrayList<IMember>().apply {
            add(5.toNumber())
            add(2.toNumber())
            add(3.toNumber())
            add(Operator.MULTIPLE)
            add(Operator.PLUS)
        }
        val converted = InfixNotationConverter().convert(exp)
        Assert.assertEquals(expected, converted)
    }

    @Test
    fun testPlusMulMinus() {
        val exp = ArrayList<IMember>().apply {
            add(5.toNumber())
            add(Operator.PLUS)
            add(2.toNumber())
            add(Operator.MULTIPLE)
            add(3.toNumber())
            add(Operator.MINUS)
            add(1.toNumber())
        }
        val expected = ArrayList<IMember>().apply {
            add(5.toNumber())
            add(2.toNumber())
            add(3.toNumber())
            add(Operator.MULTIPLE)
            add(Operator.PLUS)
            add(1.toNumber())
            add(Operator.MINUS)

        }
        val converted = InfixNotationConverter().convert(exp)
        Assert.assertEquals(expected, converted)
    }

}
