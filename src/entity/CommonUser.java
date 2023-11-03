package entity;

public class CommonUser implements User {

        private String Username;
        private final String Id;

        private final String Password;

        public CommonUser(String username, String Id, String password){
                this.Username = username;
                this.Id = Id;
                this.Password = password;


        }

        public String getName(){
                return Username;
        }

        @Override
        public void changeName(String name) {
                this.Username = name;
        }

        public String getID(){
                return this.Id;
        }

        public String getPassword() {return this.Password;}

        }

