# check_wordster

TODO: Enter the cookbook description here.

## Setup

You must have already setup the aws-cli.  The recipe will look
for credentials in the ~/.aws/credentials file, and specifically 
for the siq profile.

Chef enables you to automate your infrastructure. It provides a
command line tool called knife to help you manage your configurations.
Using the knife EC2 plugin you can manage your Amazon EC2 instances
with Chef.  knife-ec2 makes it possible to create and bootstrap Amazon
EC2 instances in just one line. But you have to go through a few setup steps.

Prepare SSH Access To Your Amazon EC2 Instances by configuring your Amazon Security Group

As Amazon blocks all incoming traffic to your EC2 instances by default,
you’ll need to open the SSH port for knife to access a newly created
instance.  Login to the AWS management console and navigate to EC2 under
Services > Compute. Go to Security Groups and select the default group.

Open the Actions dropdown and choose edit inbound rules.  Add a rule for
type SSH with Source Anywhere and save the new inbound rule.

To enable SSH access to your Amazon EC2 instances you need to create a key pair.
Amazon will install the public key of that key pair on every EC2 instance.
knife will use the private key of that key pair to connect to your Amazon EC2 instances.

Under Network & Security in AWS Management Console, select Key Pairs. Create a Key Pair.
Give it a name like "knife" so you know that this key pair will be used by knife.
Download the private key to your local workstation.  Store the downloaded private
key knife.pem in ~/.ssh/knife.pem

Open your ~/.ssh/config and add:

`Host ec2*compute-1.amazonaws.com`
`  StrictHostKeyChecking no`
`  User yournamehere`
`  IdentityFile  /home/yournamehere/.ssh/knife.pem`

Now, SSH access to your Amazon EC2 instances should work.

To enable knife to manage Amazon EC2 instances you need to install the knife EC2 plugin.

`gem install knife-ec2`

Add the AWS credentials of your knife user to your knife configuration file ~/.chef/knife.rb:

`knife[:aws_access_key_id] = "..."`
`knife[:aws_secret_access_key] = "........."`

Now you can create an aws instance:

`knife ec2 server create -r "role[ubuntu]" -I  ami-1ed88f69 -f t2.small \
  -S knife -i ~/.ssh/knife.pem --ssh-user ubuntu --region eu-west-1 -Z eu-west-1a"`

`-r "role[ubuntu]" is the run_list I want to associate with the newly created node. You can put any roles and recipes you like here`
`-I is the AMI ID you selected earlier`
`-f is the Amazon EC2 instance type (see Model)`
`-S is the name you gave to the SSH key pair generated in the AWS management console`
`-i points to the private key file of that SSH key pair as downloaded when the key pair was created in the AWS management console`
`--ssh-user the official Ubuntu EC2 AMIs use ubuntu as the default user`
`--region eu-west-1 If you want your instances to be deployed in any specific Amazon AWS region, add this parameter and the desired region`
`-Z eu-west-1a is the availability zone within your region`

## Run kitchen-cloud

KITCHEN_YAML=".kitchen-cloud.yml" kitchen test

