package com.appium.manager;

import com.appium.ios.IOSDeviceConfiguration;
import com.appium.utils.AvailablePorts;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by saikrisv on 24/01/17.
 */
public class DesiredCapabilityManager {

    private final ConfigFileManager configFileManager;
    private AvailablePorts availablePorts;
    private IOSDeviceConfiguration iosDevice;

    public DesiredCapabilityManager() throws IOException {
        configFileManager = ConfigFileManager.getInstance();
        iosDevice = new IOSDeviceConfiguration();
        availablePorts = new AvailablePorts();

    }

    public DesiredCapabilities androidNative(String userSpecifiedAndroidCaps) {
        if (!new File(userSpecifiedAndroidCaps).exists()
                && configFileManager.getProperty("ANDROID_CAPS") == null) {
            System.out.println("Setting Android Desired Capabilities:");
            DesiredCapabilities androidCapabilities = new DesiredCapabilities();
            androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
            androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                    configFileManager.getProperty("APP_ACTIVITY"));
            androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                    configFileManager.getProperty("APP_PACKAGE"));
            androidCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                    AutomationName.ANDROID_UIAUTOMATOR2);
            Path path = FileSystems.getDefault().getPath(configFileManager
                    .getProperty("ANDROID_APP_PATH"));
            if (!path.getParent().isAbsolute()) {
                androidCapabilities.setCapability(MobileCapabilityType.APP, path.normalize()
                        .toAbsolutePath().toString());
            } else {
                androidCapabilities
                        .setCapability(MobileCapabilityType.APP,
                                configFileManager.getProperty("ANDROID_APP_PATH"));
            }
            System.out.println(DeviceManager.getDeviceUDID() + Thread.currentThread().getId());
            androidCapabilities.setCapability(MobileCapabilityType.UDID,
                    DeviceManager.getDeviceUDID());
            try {
                androidCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,
                        availablePorts.getPort());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return androidCapabilities;
        }
        return null;
    }

    public DesiredCapabilities androidWeb() {
        DesiredCapabilities androidWebCapabilities = new DesiredCapabilities();
        androidWebCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        androidWebCapabilities
                .setCapability(MobileCapabilityType.BROWSER_NAME,
                        configFileManager.getProperty("BROWSER_TYPE"));
        androidWebCapabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
        androidWebCapabilities.setCapability(MobileCapabilityType.UDID,
                DeviceManager.getDeviceUDID());
        return androidWebCapabilities;
    }

    public DesiredCapabilities iosNative(String userSpecifiediOSCaps)
            throws InterruptedException, IOException {
        if (!new File(userSpecifiediOSCaps).exists()
                && configFileManager.getProperty("IOS_CAPS") == null) {
            DesiredCapabilities iOSCapabilities = new DesiredCapabilities();
            System.out.println("Setting iOS Desired Capabilities:");
            iOSCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                    "iOS");
            iOSCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                    "10.0");
            Path path = FileSystems.getDefault().getPath(configFileManager
                    .getProperty("IOS_APP_PATH"));
            if (!path.getParent().isAbsolute()) {
                iOSCapabilities.setCapability(MobileCapabilityType.APP, path.normalize()
                        .toAbsolutePath().toString());
            } else {
                iOSCapabilities.setCapability(MobileCapabilityType.APP,
                        configFileManager.getProperty("IOS_APP_PATH"));
            }
            iOSCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
            iOSCapabilities
                    .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
            iOSCapabilities.setCapability(MobileCapabilityType.UDID, DeviceManager.getDeviceUDID());
            if (iosDevice.getIOSDeviceProductVersion()
                    .contains("10")) {
                iOSCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                        AutomationName.IOS_XCUI_TEST);
                try {
                    iOSCapabilities.setCapability(IOSMobileCapabilityType
                            .WDA_LOCAL_PORT, availablePorts.getPort());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return iOSCapabilities;
        }
        return null;
    }


}
