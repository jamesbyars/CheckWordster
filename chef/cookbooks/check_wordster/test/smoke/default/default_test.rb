# # encoding: utf-8

# Inspec test for recipe check_wordster::default

# The Inspec reference, with examples and extensive documentation, can be
# found at http://inspec.io/docs/reference/resources/



#describe port(80) do
#  it { should_not be_listening }
#  skip 'This is an example test, replace with your own test.'
#end

# describe package 'mysql_service' do
#   it { should be_installed }
# end

# describe service 'mysql_service' do
#   it { should be_enabled }
#   it { should be_running }
# end

describe command 'sestatus | grep "Current mode:"' do
  its('stdout') { should match /permissive/ }
end
