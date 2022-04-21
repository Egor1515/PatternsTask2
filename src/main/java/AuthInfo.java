import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthInfo {
    private static Faker faker;

    private String login = faker.funnyName().name();
    private String password = faker.numerify("#######");
    private String status = faker.expression("active || blocked");

}
