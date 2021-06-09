package meetingrooms;

import java.util.Scanner;

public class MeetingRoomsController {

    public static String RUN_MODE = "ON";
    private MeetingRoomsService meetingRoomsService;
    private Scanner scanner = new Scanner(System.in);

    public MeetingRoomsController(MeetingRoomsService meetingRoomsService) {
        this.meetingRoomsService = meetingRoomsService;
    }

    public void start() {
        setMode();
        String name;
        while (RUN_MODE == "ON") {
            loadMenu();
            int menuPoint = Integer.parseInt(scanner.nextLine());
            switch (menuPoint) {
                case 0:

                    System.out.println("Tárgyaló neve: ");
                    name = scanner.nextLine();
                    System.out.println("Tárgyaló hossza: ");
                    int length = Integer.parseInt(scanner.nextLine());
                    System.out.println("Tárgyaló szélessége: ");
                    int width = Integer.parseInt(scanner.nextLine());
                    meetingRoomsService.save(new MeetingRoom(name, length, width));
                    break;
                case 1:
                    meetingRoomsService.alphabetic();
                    break;
                case 2:
                    meetingRoomsService.alphabeticReversed();
                    break;
                case 3:
                    meetingRoomsService.listEverySecond();
                    break;
                case 4:
                    meetingRoomsService.getAreaMap();
                    break;
                case 5:
                    System.out.println("Tárgyaló neve: ");
                    name = scanner.nextLine();
                    meetingRoomsService.getRoomPerName(name);
                    break;
                case 6:
                    System.out.println("Névtöredék: ");
                    String namePart = scanner.nextLine();
                    meetingRoomsService.getRoomPerNamePart(namePart);
                    break;
                case 7:
                    System.out.println("Névtöredék: ");
                    int area = Integer.parseInt(scanner.nextLine());
                    meetingRoomsService.getRoomPerArea(area);
                    break;
                case 8:
                    System.out.println("Kilépés");
                    RUN_MODE = "OFF";


            }
        }

    }


    private void setMode() {
        System.out.println("Adatbázisba vagy memóriába mentsek? (1 = Adatbázis, 2 = Memória): " + '\n');
        int mode = Integer.parseInt(scanner.nextLine());
        if (mode == 1) {
            meetingRoomsService.setMeetingRoomsRepository(new MariadbMeetingRoomsRepository());
        } else if (mode == 2) {
            meetingRoomsService.setMeetingRoomsRepository(new InMemoryMeetingRoomsRepository());
        } else {
            System.out.println("Nem megfelelő kiválasztás. Kilépés.");
            return;
        }
    }


    private void loadMenu() {
        System.out.println("0. Tárgyaló rögzítése");
        System.out.println("1. Tárgyalók névsorrendben");
        System.out.println("2. Tárgyalók név alapján visszafele sorrendben");
        System.out.println("3. Minden második tárgyaló");
        System.out.println("4. Területek");
        System.out.println("5. Keresés pontos név alapján");
        System.out.println("6. Keresés névtöredék alapján");
        System.out.println("7. Keresés terület alapján");
        System.out.println("8. Kilépés");
        System.out.println();
        System.out.println("Hányadik menőpontot hajtsam végre?");
    }


    public static void main(String[] args) {
        MeetingRoomsController controller = new MeetingRoomsController(new MeetingRoomsService());
        controller.start();
    }
}
