package org.firstinspires.ftc.teamcode.oldcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
    public class auto extends OpMode {
        MechanumDrive drive = new MechanumDrive();
        @Override
        public void init() {drive.init(hardwareMap);}
        @Override
        public void loop() {drive.drive(0.1, 0, 0);}
    }