package assignment7;


class Root {
  public static void main(String[] args) {
    
    Device device = new Device();
    Sensor heat = new Sensor(device);
    Sensor pressure = new Sensor(device);

    Controller controller = new Controller(device, heat, pressure);

    controller.start();
    heat.start();
    pressure.start();
  }
}
