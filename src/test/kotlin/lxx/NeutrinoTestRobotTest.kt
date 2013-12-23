package lxx

import org.junit.Test
import robocode.StatusEvent
import robocode.DeathEvent
import lxx.math.*
import kotlin.test.assertTrue

class NeutrinoTestRobotTest {

    [Test]
    fun testTestRobot() {
        val robot = NeutrinoTestRobot()
        val status = RobotStatus(x = 0.0, y = 0.0, time = 0L, bodyHeading = RADIANS_90, radarHeading = RADIANS_90)
        val event = StatusEvent(status)
        robot.onStatus(event)

        val robotThread = Thread(robot)
        robotThread.start()
        robot.onStatus(StatusEvent(RobotStatus(x = 0.0, y = 0.0, time = 0L, bodyHeading = RADIANS_90, radarHeading = RADIANS_90)))
        robot.onDeath(DeathEvent())

        synchronized (robot) {
            robot.notify()
        }

        robotThread.join()
        assertTrue(robot.setTurnRadarRightCalled)
    }

}