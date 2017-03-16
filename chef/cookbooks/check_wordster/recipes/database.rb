#
# Cookbook:: check_wordster
# Recipe:: database
#
# Copyright:: 2017, The Authors, All Rights Reserved.

mysql_service node['check_wordster']['database']['mysql_service_name'] do
  version node['check_wordster']['database']['mysql_version']
  bind_address node['check_wordster']['database']['bind_address']
  port node['check_wordster']['database']['port']
  data_dir node['check_wordster']['database']['data_dir']
  initial_root_password node['check_wordster']['database']['root_password']
  action [:create, :start]
end
