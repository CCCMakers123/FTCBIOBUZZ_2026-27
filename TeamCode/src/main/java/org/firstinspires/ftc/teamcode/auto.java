package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.MechanumDrive;
    @Autonomous
    public class auto extends OpMode {
        MechanumDrive drive = new MechanumDrive();
        @Override
        public void init() {drive.init(hardwareMap);}
        @Override
        public void loop() {drive.drive(0.1, 0, 0);}
    }