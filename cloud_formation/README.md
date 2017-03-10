# Create KeyPair

`aws ec2 create-key-pair --key-name chefkey --query 'KeyMaterial' --output text > chefkey.pem`

`chmod 400 chefkey.pem`


# Creating The Development Environment

## CloudFormation

### Upload template to S3

`aws s3 cp ./dev-env-stack.yaml s3://cf-templates-01`

### Validate Template

`aws cloudformation validate-template --template-body file://dev-env-stack.yaml`

`aws cloudformation validate-template --template-url s3://cf-templates-01/dev-env-stack.yaml`

### Create Stack

`aws cloudformation create-stack --stack-name DevEnvironment --template-url http://s3.amazonaws.com/cf-templates-01/dev-env-stack.yaml`

### Delete stack

`aws cloudformation delete-stack --stack-name DevEnvironment`

### Validate can connect to DB from EC2

`nc -yz <db hostname> <port>`