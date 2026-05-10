
# EmployeeManagement_Springboot

java -version

sudo apt update
sudo apt install openjdk-17-jdk -y

sudo update-alternatives --config java

sudo update-alternatives --config javac


export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH


echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc


java -version
javac -version

openjdk version "17"




Extension Pack for Java
Spring Boot Extension Pack
Lombok Annotations Support


curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,lombok,postgresql,validation \
  -d javaVersion=17 \
  -d type=maven-project \
  -d groupId=com.example \
  -d artifactId=demo \
  -o demo.zip


unzip demo.zip
cd demo


mvn -version

If Maven 4 → ⚠️ downgrade (Spring Boot stable with Maven 3):

sudo apt remove maven -y

sudo apt update
sudo apt install maven -y

mvn -version

If Still Showing Maven 4 (Important):


which mvn

export PATH=/usr/bin:$PATH

mvn -version



Build Project:

mvn clean install


Run Application:

mvn spring-boot:run


Run PostgreSQL container

docker run -d \
  --name postgres-db \
  -e POSTGRES_DB=ecommerce \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:15

Run postgres:

docker exec -it <container-id> psql -U postgres -d <db_name>



mvn clean install -DskipTests


https://chatgpt.com/share/69da2aba-d190-8321-b0ee-bf335c5ce2a1


nano ~/.bashrc
Keep ONLY these TWO clean lines:

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

Nothing else.
Then save:

Ctrl + O
Enter
Ctrl + X

Now reload:

source ~/.bashrc
