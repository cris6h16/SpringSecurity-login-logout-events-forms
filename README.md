**PD:** this repo is only an example...   


## screenshots:
1. GET `/`  
![img.png](img.png)  

#

2. GET `/login`  
![img_1.png](img_1.png)
```java
UserDetails user = User.withDefaultPasswordEncoder() // you can do your own impl for add more attributes
                .username("cris6h16")
                .password("cris6h16") // password is stored encrypted
                .roles("USER")
                .build();
```
#
3. GET `/auth/info` (logged in)      
![img_2.png](img_2.png)
#
3. GET `/logout` (logged in isn't necessary)   
![img_3.png](img_3.png)
