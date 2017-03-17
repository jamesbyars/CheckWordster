#
# Cookbook:: check_wordster
# Recipe:: default
#
# Copyright:: 2017, The Authors, All Rights Reserved.
# include_recipe 'selinux::disabled'
include_recipe 'apt'
include_recipe 'check_wordster::database'