package entity;

public class CommonUser implements User {

        private String Username;
        private final String Id;

        public CommonUser(String username, String Id){
                this.Username = username;
                this.Id = Id;

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


        }

