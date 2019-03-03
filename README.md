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