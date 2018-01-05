import com.santaev.core.entity.Expression
import com.santaev.core.util.toNumber
import org.junit.Assert
import org.junit.Test

class ExpressionTest {

    private val delta = 1E-10

    @Test
    fun testSingleIntNumber() {
        val exp = Expression()
        exp.addMember(5.toNumber())
        Assert.assertEquals(exp.calculate().value, 5.0, delta)
    }
}
