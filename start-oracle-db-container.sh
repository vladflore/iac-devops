docker run -d --name odb-1930 \
-p 1521:1521 -p 5500:5500 \
-e ORACLE_SID=ORCLCDB \
-e ORACLE_PDB=ORCLPDB1 \
-e ORACLE_PWD=welcome1 \
-e ORACLE_CHARACTERSET=AL32UTF8 \
-v /opt/oracle/oradata \
oracle/database:19.3.0-ee
