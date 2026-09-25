package org.firstinspires.ftc.teamcode;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.paths.Path;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class MechanumTestAuto extends OpMode {

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();
    private final Pose startPose = p.of(8.294, 9.546, 0);
    private final Pose topLeft = p.of(10.503,131.632,0);
    private final Pose topRight = p.of(35.736,131.767, 0);
    private final Pose bottomRight = p.of(36.031,9.613,0);
    private Path leftSide()
    {
        return line(startPose, topLeft).tangent();
    }
    private Path Top()
    {
        return line(topLeft, topRight).tangent();
    }
    private Path rightSide()
    {
        return line(topRight, bottomRight).tangent();
    }
    private Path Bottom()
    {
        return line(bottomRight,startPose).tangent();
    }
    private Command square()
    {
        return sequential
                (follow(follower,leftSide()), follow(follower, Top()), follow(follower, rightSide()), follow(follower, Bottom()));
    }


    @Override
    public void init()
    {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {schedule(square());}

    @Override
    public void loop()
    {
        follower.update();
        Scheduler.execute();
    }
}
