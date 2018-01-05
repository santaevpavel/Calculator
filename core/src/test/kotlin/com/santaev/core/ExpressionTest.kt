import com.santaev.core.entity.Expression
import com.santaev.core.entity.member.Operator
import com.santaev.core.util.toNumber
import org.junit.Assert
import org.junit.Test

class ExpressionTest {

    private val delta = 1E-10

    @Test
    fun testSingleIntNumber() {
        val exp = Expression()
        exp.addMember(5.toNumber())
        Assert.assertEquals(5.0, exp.calculate().value, delta)
    }

    @Test
    fun testSingleDoubleNumber() {
        val exp = Expression()
        exp.addMember(5.123.toNumber())
        Assert.assertEquals(5.123, exp.calculate().value, delta)
    }

    @Test
    fun testPlusDoubleNumber() {
        val exp = Expression()
        exp.addMember(1.2.toNumber())
        exp.addMember(Operator.PLUS)
        exp.addMember(2.5.toNumber())
        Assert.assertEquals(3.7, exp.calculate().value, delta)
    }

    @Test
    fun testMinusDoubleNumber() {
        val exp = Expression()
        exp.addMember(1.2.toNumber())
        exp.addMember(Operator.MINUS)
        exp.addMember(2.5.toNumber())
        Assert.assertEquals(-1.3, exp.calculate().value, delta)
    }

    @Test
    fun testMulDoubleNumber() {
        val exp = Expression()
        exp.addMember(1.2.toNumber())
        exp.addMember(Operator.MULTIPLE)
        exp.addMember(2.toNumber())
        Assert.assertEquals(2.4, exp.calculate().value, delta)
    }

    @Test
    fun testDivDoubleNumber() {
        val exp = Expression()
        exp.addMember(2.2.toNumber())
        exp.addMember(Operator.DIVISION)
        exp.addMember(2.toNumber())
        Assert.assertEquals(1.1, exp.calculate().value, delta)
    }
}
