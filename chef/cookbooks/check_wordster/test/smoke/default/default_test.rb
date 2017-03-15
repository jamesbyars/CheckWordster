# # encoding: utf-8

# Inspec test for recipe check_wordster::default

# The Inspec reference, with examples and extensive documentation, can be
# found at http://inspec.io/docs/reference/resources/

describe command 'sestatus | grep "Current mode:"' do
  its('stdout') { should match /permissive/ }
end
