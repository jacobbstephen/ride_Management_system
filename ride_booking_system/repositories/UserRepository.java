package ride_booking_system.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ride_booking_system.entity.User;
import ride_booking_system.repositories.headers.UserCSVHeaders;

public class UserRepository implements RepositoryInterface<User>, UserCSVHeaders {
    private final List<User> users;
    private final String path = "ride_booking_system/data/users.csv";

    public UserRepository() {
        users = new ArrayList<>();
        // load();
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> getAll() {
        return users;
    }

    public int size() {
        return users.size();
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 3) continue;

                int id = Integer.parseInt(fields[ID]);
                String name = fields[NAME];
                String phoneNumber = fields[PHONE_NUMBER];

                User user = new User(name, phoneNumber);
                user.setId(id);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        try (
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer)
        ) {
            bufferedWriter.write("Id,Name,PhoneNumber");
            bufferedWriter.newLine();

            for (User user : users) {
                String line = makeString(user);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String makeString(User user) {
        return String.join(",",
            String.valueOf(user.getId()),
            user.getName(),
            user.getPhoneNumber()
        );
    }
}
