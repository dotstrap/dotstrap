require 'colorize'
require 'logger'

module Dotstrap
  # TODO: split this into Git < Downloader class
  class Dotstrap::Git
    attr_accessor :url, :repo,
                  :repo_path, :repo_name,
                  :github_user, :github_project

    def initialize(repo, src_dir = Dotstrap.config_home)
      @repo = repo
      # TODO: allow an option to specify to download with SSH or HTTPs from Git
      @url = "https://github.com/#{@repo}"
      partition = @repo.partition("/")
      @github_user = partition[0]
      @github_project = @repo_name = partition[2]
      @repo_path = File.join(src_dir, "#{@github_user}-#{@repo_name}")
      # @repo_path = File.join(src_dir, @github_user, @repo_name)
    end

    # FIXME: if user is not logged in to Git the prompt for username/password
    # is mangled because the threads are not synchronized
    def clone(url = @url, dir = @repo_path, repo = @repo)
      if Dir.exist?(dir)
        pull(dir)
        return
      end
      `git clone #{$LOG.debug? ? '' : '-q'} #{url} #{dir}`
      $LOG.debug { "CLONE #{repo} #{url} #{repo_path}" }
      $LOG.unknown { "=> ".colorize(:blue) + "#{repo}\ndownloaded" }
    end

    def pull(dir = @repo_path, repo = @repo)
      `git pull #{$LOG.debug? ? '' : '-q'}`
      $LOG.debug { "PULL #{repo} #{url} #{repo_path}" }
      $LOG.unknown { "=> ".colorize(:blue) + "#{repo}\nupdated" }
    end
  end
end
