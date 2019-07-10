package io.muic.ooc;

import io.muic.ooc.formatconverter.FormatConverterDTO;
import io.muic.ooc.formatconverter.FormatConverterService;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinalApplicationTests {

    @Autowired
    private FormatConverterService formatConverterService;

    @Test
    public void testErrorInput() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "ckass", "camelCase")))
                .isEqualTo(null);
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "CamelCase")))
                .isEqualTo(null);
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "properly", "snake_case")))
                .isEqualTo(null);
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "snakecase")))
                .isEqualTo(null);
    }

    @Test
    public void testStrangeInput(){
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User@", "class", "camelCase")))
                .isEqualTo("user@");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User@Profile", "property", "camelCase")))
                .isEqualTo("user@Profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User@", "class", "snake_case")))
                .isEqualTo("user_@");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User@Profile", "property", "snake_case")))
                .isEqualTo("user_@_profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User1", "class", "lowercase")))
                .isEqualTo("user1");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User1Profile", "property", "lowercase")))
                .isEqualTo("user1profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("User/", "class", "UPPERCASE")))
                .isEqualTo("USER/");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("user/Profile", "property", "UPPERCASE")))
                .isEqualTo("USER/PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("@@", "class", "kebab-case")))
                .isEqualTo("@@");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123", "property", "kebab-case")))
                .isEqualTo("123");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123", "class", "PascalCase")))
                .isEqualTo("123");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123", "property", "PascalCase")))
                .isEqualTo("123");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "class", "OracleFriendly")))
                .isEqualTo("123_@!#_$");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "property", "OracleFriendly")))
                .isEqualTo("123_@!#_$");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "class", "MySQLFriendlyWithPrefix")))
                .isEqualTo("tbl_123_@!#_$");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "property", "MySQLFriendlyWithPrefix")))
                .isEqualTo("123_@!#_$");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "class", "OracleFriendlyWithPrefix")))
                .isEqualTo("TBL_123_@!#_$");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("123@!#$", "property", "OracleFriendlyWithPrefix")))
                .isEqualTo("123_@!#_$");

    }

    @Test
    public void testCamelCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "camelCase")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "camelCase")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "camelCase")))
                .isEqualTo("userProfile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "camelCase")))
                .isEqualTo("userProfile");
    }

    @Test
    public void testSnakeCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "snake_case")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "snake_case")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "snake_case")))
                .isEqualTo("user_profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "snake_case")))
                .isEqualTo("user_profile");
    }

    @Test
    public void testLowerCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "lowercase")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "lowercase")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "lowercase")))
                .isEqualTo("userprofile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "lowercase")))
                .isEqualTo("userprofile");
    }

    @Test
    public void testUpperCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "UPPERCASE")))
                .isEqualTo("PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "UPPERCASE")))
                .isEqualTo("PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "UPPERCASE")))
                .isEqualTo("USERPROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "UPPERCASE")))
                .isEqualTo("USERPROFILE");
    }

    @Test
    public void testKebabCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "kebab-case")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "kebab-case")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "kebab-case")))
                .isEqualTo("user-profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "kebab-case")))
                .isEqualTo("user-profile");
    }

    @Test
    public void testPascalCase() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "PascalCase")))
                .isEqualTo("Profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "PascalCase")))
                .isEqualTo("Profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "PascalCase")))
                .isEqualTo("UserProfile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "PascalCase")))
                .isEqualTo("UserProfile");
    }

    @Test
    public void testMySQLFriendly() {
        testSnakeCase();
    }

    @Test
    public void testOracleFriendly() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "OracleFriendly")))
                .isEqualTo("PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "OracleFriendly")))
                .isEqualTo("PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "OracleFriendly")))
                .isEqualTo("USER_PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "OracleFriendly")))
                .isEqualTo("USER_PROFILE");
    }

    @Test
    public void testMySQLFriendlyWithPrefix() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "MySQLFriendlyWithPrefix")))
                .isEqualTo("tbl_profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "MySQLFriendlyWithPrefix")))
                .isEqualTo("profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "MySQLFriendlyWithPrefix")))
                .isEqualTo("tbl_user_profile");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "MySQLFriendlyWithPrefix")))
                .isEqualTo("user_profile");
    }

    @Test
    public void testOracleFriendlyWithPrefix() {
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("Profile", "class", "OracleFriendlyWithPrefix")))
                .isEqualTo("TBL_PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("profile", "property", "OracleFriendlyWithPrefix")))
                .isEqualTo("PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("UserProfile", "class", "OracleFriendlyWithPrefix")))
                .isEqualTo("TBL_USER_PROFILE");
        assertThat(formatConverterService.convert(
                new FormatConverterDTO("userProfile", "property", "OracleFriendlyWithPrefix")))
                .isEqualTo("USER_PROFILE");
    }

    @Test
    public void finalTestCases(){
        File file;
        Scanner scanner = null;
        try{
            file = new File("src/test/resources/test-cases.csv");
            scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String[] testcase = scanner.nextLine().split(",");
                for (int i = 0; i < testcase.length; i++) {
                    testcase[i] = testcase[i].trim();
                }
                assertThat(formatConverterService.convert(
                new FormatConverterDTO(testcase[0], testcase[1], testcase[2])))
                .isEqualTo(testcase[3]);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }
}
