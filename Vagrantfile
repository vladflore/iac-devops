# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.



$script = <<-SCRIPT
echo Restricting permission for private key file...
chmod 400 ~/.ssh/id_rsa
SCRIPT




Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  # config.vm.box = "base"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  # config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  # config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
  #   vb.memory = "1024"
  # end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  # config.vm.provision "shell", inline: <<-SHELL
  #   apt-get update
  #   apt-get install -y apache2
  # SHELL

  #########################################################################################

    config.vm.provider 'virtualbox' do |vb|
      vb.name = 'jenkins-running-in-docker'
      vb.cpus = 2
      vb.memory = 4096
    end

    unless Vagrant.has_plugin?("vagrant-vbguest")
        raise 'The Vagrant VBGuest plugin must be install prior to building this VM (vagrant plugin install vagrant-vbguest)'
    end

    config.vm.box = "ubuntu/xenial64"

    dockerfiles_dir = "/var/lib/dockerfiles"
    jenkins_dir = "/var/lib/jenkins"
    docker_sock_path = "/var/run/docker.sock"

    config.vm.provision "docker", images: ["jenkins/jenkins:lts"] do |docker|
      docker.build_image dockerfiles_dir, args: "-f #{dockerfiles_dir}/jenkins_docker -t jenkins/docker --build-arg DOCKER_GROUP_ID=$(getent group docker | cut -d: -f3)"
      docker.run "jenkins/docker", args: "-p 8080:8080 -p 50000:50000 -v #{jenkins_dir}:#{jenkins_dir} -v #{docker_sock_path}:#{docker_sock_path} -v $(which docker):/usr/bin/docker"
    end

    # TODO how to change the file permission to 400 ?
    config.vm.provision "file", source: "C:\\Users\\Vlad\\.ssh\\id_rsa", destination: "~/.ssh/id_rsa"
    # config.vm.provision "shell", inline: $script

    config.vm.network "forwarded_port", guest: 8080, host: 8980

    config.vm.synced_folder "./jenkins_home", jenkins_dir
    config.vm.synced_folder "./dockerfiles", dockerfiles_dir

    # disable the default share
    config.vm.synced_folder ".", "/vagrant", disabled: true
  #########################################################################################
end
