public class UserDataProcessor {

    private static class UserData {
        String lastName;
        String firstName;
        String middleName;
        String birthDate;
        long phoneNumber;
        char gender;

        public UserData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
        }
    }

    private static UserData parseUserData(String userData) {
        String[] userDataArray = userData.split(" ");
        if (userDataArray.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных");
        }

        String lastName = userDataArray[0];
        String firstName = userDataArray[1];
        String middleName = userDataArray[2];
        String birthDate = userDataArray[3];
        long phoneNumber = Long.parseLong(userDataArray[4]);
        char gender = userDataArray[5].charAt(0);

        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверный формат пола");
        }

        return new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static void writeUserDataToFile(UserData userData) throws IOException {
        String fileName = userData.lastName + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(userData.lastName + " " + userData.firstName + " " +
                    userData.middleName + " " + userData.birthDate + " " +
                    userData.phoneNumber + " " + userData.gender + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String userData = scanner.nextLine().trim();

        try {
            UserData parsedData = parseUserData(userData);
            writeUserDataToFile(parsedData);
            System.out.println("Данные успешно записаны в файл " + parsedData.lastName + ".txt");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Неверный формат номера телефона");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }
}