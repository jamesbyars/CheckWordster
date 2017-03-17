# # encoding: utf-8

# Inspec test for recipe check_wordster::database

# The Inspec reference, with examples and extensive documentation, can be
# found at http://inspec.io/docs/reference/resources/

describe service "mysql-default" do
 it { should be_enabled}
 it { should be_running}
end