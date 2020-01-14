# iac-devops
infrastructure as code

* **machine:**
    * Operating System: ```Ubuntu 16.04.5 LTS```
    * OSType: ```linux```
    * Architecture: ```x86_64```
    * CPUs: ```2```
    * Total Memory: ```992MiB```
    * Name: ```ubuntu-xenial```

* **docker:**

    * Docker version 18.09.3, build 774a1f4

* **jenkins:**

    * http://localhost:8980/

# Nice to know

* **how to get the list of plugins from an existing server:**

```JENKINS_HOST=username:password@myhost.com:port```

```curl -sSL "http://$JENKINS_HOST/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/'```

More: ```https://github.com/jenkinsci/docker```

# Oracle database in Docker
* Go [here](https://github.com/oracle/docker-images)
* clone the entire repository
* navigate to `<where_you_cloned_the_repo>/docker-images/OracleDatabase/SingleInstance/dockerfiles` and decide for a version e.g. `19.3.0` 
* download the binaries for that version (follow the [documentation](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance))
* build the docker image based on that version (follow the [documentation](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance))
* w a i t...
    * Build completed in **1486** seconds.
* follow the instructions from [here](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance#running-oracle-database-in-a-docker-container) to start a container
* w a i t... some more
    * alternatively use script to start container in detached mode
    * `./start-oracle-db-container.sh`
    * TODO: after `docker run` do `docker logs -f <container_id>`, where `container_id` comes from the previous command
* reset password by running `docker exec <container_name> ./setPassword.sh <your_password>`

# Oracle XE database in Docker
```
cd /home/vlad/vladflore.dev/sandbox/docker-images/OracleDatabase/SingleInstance/dockerfiles
./buildDockerImage.sh -v 11.2.0.2 -x
```
* Go [here](https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance#running-oracle-database-11gr2-express-edition-in-a-docker-container)
```
docker volume create odb-xe-11202
docker run --name odb-xe-11202 \
--shm-size=1g \
-p 1521:1521 -p 8080:8080 \
-e ORACLE_PWD=welcome1 \
-v odb-xe-11202:/u01/app/oracle/oradata \
oracle/database:11.2.0.2-xe
```












```
docker login
docker run -d -it --name <Oracle-DB> store/oracle/database-enterprise:12.2.0.1
docker ps
docker exec -it <Oracle-DB> bash -c "source /home/oracle/.bashrc; sqlplus /nolog"
docker run -d -it --name <Oracle-DB> -P store/oracle/database-enterprise:12.2.0.1
docker port <Oracle-DB>

ORCLCDB=(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=<ip-address of host>)(PORT=<mapped host port>))
    (CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ORCLCDB.localdomain)))
ORCLPDB1=(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=<ip-address> of host)(PORT=<mapped host port>))
    (CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=ORCLPDB1.localdomain)))

sqlplus sys/Oradoc_db1@ORCLCDB as sysdba

alter user sys identified by <new-password>;

docker logs <Oracle-DB>

docker run -d -it --name <Oracle-DB> -v OracleDBData:/ORCL store/oracle/database-enterprise:12.2.0.1

docker run -d -it --name <Oracle-DB> -v /data/OracleDBData:/ORCL store/oracle/database-enterprise:12.2.0.1

docker run -d -it --name <Oracle-DB> store/oracle/database-enterprise:12.2.0.1-slim
```

[docker hub](https://hub.docker.com/u/vladflore/content/sub-49361cea-8599-463d-b3cd-bf5d4f428420) 
 
 
# Install sqldeveloper on ubuntu
* go to [link](https://www.oracle.com/tools/downloads/sqldev-v192-downloads.html)
* download the .rpm
* you are on ubuntu so you need .deb
* install alien (facilitates conversion from rpm to deb)
    * `sudo apt-get install alien dpkg-dev debhelper build-essential`
    * `sudo alien <installation_package>.rpm`
        * you will get a .deb
* install the deb package `sudo dpkg -i <generated_installation_package>.deb`
* start sqldeveloper and detach process: `nohup sqldeveloper &`

# other useful commands
* `readlink -f $(which java)`
