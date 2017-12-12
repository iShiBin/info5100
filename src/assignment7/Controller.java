package assignment7;

import java.util.Random;

/**
 * 3. Write a class Controller (extends Thread) that can poll the sensors
 * concurrently to running the device. You should implement its run() method
 * such that it starts the device and then monitors it by waiting for and
 * examining any new sensor values. The controller shuts down the device if the
 * heat sensor exceeds the value 70 or the pressure sensor the value 100. Also
 * complete the run() method in the class Sensor which calls updateValue()
 * continuously and signals the controller if its value has changed. You can
 * print the heat and pressure value to console in the run() method of
 * Controller to check.
 * 
 * @author bin
 *
 */

class Controller extends Thread { // score 2
  private Device device;
  private Sensor heat, pressure;

  public Controller(Device device, Sensor heat, Sensor pressure) {
    this.device = device;
    this.heat = heat;
    this.pressure = pressure;
    this.device.startup();
  }

  @Override
  public void run() {
    while (true) {
      try {
        synchronized (device) {
          double h = this.heat.getValue();
          double p = this.pressure.getValue();
          if (h <= 70 && p <= 100) {
            System.out.printf("heat ->  %.2f, pressure -> %.2f\n", h, p);
            device.wait();
          } else {
            this.device.shutdown();
            break;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

class Device {
  public void startup() {
    System.out.println("Device started");
  } // print to console that device is starting

  public void shutdown() {
    System.out.println("Device shutting down due to maintenance");
    System.exit(0);
  } // print to console that device is shutting down and exit
}

class Sensor extends Thread {
  private final Device device;
  private double value;

  public Sensor(Device device) {
    this.device = device;
  }

  public double getValue() {
    return value;
  }

  public void updateValue() {
    // increase by adding a random double value
    this.value += Math.random();
  }

  @Override
  public void run() {
    while (true) {
      try {
        synchronized (device) {
          this.updateValue();
          device.notify();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
