# Generated by jeweler
# DO NOT EDIT THIS FILE DIRECTLY
# Instead, edit Jeweler::Tasks in Rakefile, and run 'rake gemspec'
# -*- encoding: utf-8 -*-
# stub: dotstrap 0.4.1 ruby lib

Gem::Specification.new do |s|
  s.name = "dotstrap"
  s.version = "0.4.1"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.metadata = { "documentation" => "http://www.rubydoc.info/github/mkwmms/dotstrap/master/Dotstrap", "source_code" => "http://github.com/mkwmms/dotstrap" } if s.respond_to? :metadata=
  s.require_paths = ["lib"]
  s.authors = ["William Myers"]
  s.date = "2015-12-23"
  s.description = "bootstrap your shell dotfiles in parallel from GitHub repos"
  s.executables = ["dotstrap"]
  s.extra_rdoc_files = [
    "LICENSE.txt",
    "README.md"
  ]
  s.files = [
    ".document",
    ".rspec",
    "CONTRIBUTING.md",
    "Gemfile",
    "LICENSE.txt",
    "README.md",
    "Rakefile",
    "bin/dotstrap",
    "dotstrap.gemspec",
    "lib/dotstrap.rb",
    "lib/dotstrap/git.rb",
    "lib/dotstrap/shell.rb",
    "spec/bundle_spec.rb",
    "spec/shell_spec.rb",
    "spec/spec_helper.rb"
  ]
  s.homepage = "http://github.com/mkwmms/dotstrap"
  s.licenses = ["MIT"]
  s.required_ruby_version = Gem::Requirement.new(">= 1.9.2")
  s.rubygems_version = "2.4.5.1"
  s.summary = "bootstrap your shell dotfiles in parallel from GitHub repos"

  if s.respond_to? :specification_version then
    s.specification_version = 4

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<colorize>, ["~> 0.7"])
      s.add_runtime_dependency(%q<parallel>, ["~> 1.6"])
      s.add_runtime_dependency(%q<thor>, ["~> 0.19"])
      s.add_development_dependency(%q<rspec>, ["~> 3.3"])
      s.add_development_dependency(%q<yard>, ["~> 0.7"])
      s.add_development_dependency(%q<rdoc>, ["~> 3.12"])
      s.add_development_dependency(%q<bundler>, ["~> 1.0"])
      s.add_development_dependency(%q<jeweler>, ["~> 2.0"])
      s.add_development_dependency(%q<simplecov>, ["~> 0.10"])
      s.add_development_dependency(%q<factory_girl>, ["~> 4.0"])
    else
      s.add_dependency(%q<colorize>, ["~> 0.7"])
      s.add_dependency(%q<parallel>, ["~> 1.6"])
      s.add_dependency(%q<thor>, ["~> 0.19"])
      s.add_dependency(%q<rspec>, ["~> 3.3"])
      s.add_dependency(%q<yard>, ["~> 0.7"])
      s.add_dependency(%q<rdoc>, ["~> 3.12"])
      s.add_dependency(%q<bundler>, ["~> 1.0"])
      s.add_dependency(%q<jeweler>, ["~> 2.0"])
      s.add_dependency(%q<simplecov>, ["~> 0.10"])
      s.add_dependency(%q<factory_girl>, ["~> 4.0"])
    end
  else
    s.add_dependency(%q<colorize>, ["~> 0.7"])
    s.add_dependency(%q<parallel>, ["~> 1.6"])
    s.add_dependency(%q<thor>, ["~> 0.19"])
    s.add_dependency(%q<rspec>, ["~> 3.3"])
    s.add_dependency(%q<yard>, ["~> 0.7"])
    s.add_dependency(%q<rdoc>, ["~> 3.12"])
    s.add_dependency(%q<bundler>, ["~> 1.0"])
    s.add_dependency(%q<jeweler>, ["~> 2.0"])
    s.add_dependency(%q<simplecov>, ["~> 0.10"])
    s.add_dependency(%q<factory_girl>, ["~> 4.0"])
  end
end

