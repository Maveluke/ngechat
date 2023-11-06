package entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class CommonUser implements User {

        private String Username;
        private final String Id;

        private final String Password;


                private static String randomInt() {
                        Random random = new Random();

                        // defining the lower and upper bounds

                        int lowerBound = 10;
                        int upperBound = 100;
                        HashMap<String, User> hashMap = ;
                        Collection<User> valuesCollection = hashMap.values();
                        // Generate a random integer between lowerBound (inclusive) and upperBound (exclusive)
                        while (true) {
                                int randomInt = random.nextInt(upperBound - lowerBound) + lowerBound;
                                for (User value : valuesCollection) {
                                        if (Integer.parseInt(value.getID()) == randomInt) {
                                                break;
                                        }
                                        return Integer.toString(randomInt);
                                }


                                }
                        }

                        public CommonUser(String username, String password) {
                                this.Username = username;
                                this.Password = password;
                                this.Id = randomInt();


                        }


                        public String getName () {
                                return Username;
                        }

                        @Override
                        public void changeName (String name){
                                this.Username = name;
                        }

                        public String getID () {
                                return Id;
                        }

                        public String getPassword () {
                                return this.Password;
                        }

                }



