# # encoding: utf-8

# Inspec test for recipe check_wordster::default

# The Inspec reference, with examples and extensive documentation, can be
# found at http://inspec.io/docs/reference/resources/

describe command 'sudo netstat -anp | grep 3306' do
  its('stdout') { should match /LISTEN/}
  its('stdout') { should match /mysqld/}
end
