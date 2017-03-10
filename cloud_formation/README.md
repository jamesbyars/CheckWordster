# Upload template to S3

`aws s3 cp ./stack.yaml s3://cf-templates-01`

# Validate Template

`aws cloudformation validate-template --template-body file://stack.yaml`

`aws cloudformation validate-template --template-url s3://cf-templates-01/stack.template`

# Create Stack

aws cloudformation create-stack --stack-name single-server --template-url http://s3.amazonaws.com/cf-templates-01/stack.template

# Delete stack

aws cloudformation delete-stack --stack-name single-server