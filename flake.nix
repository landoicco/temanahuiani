{
  description = "Temanahuiani - a tower defense engine and demo game in vanilla Java";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = { nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
      jdk = pkgs.jdk;

      # Not in nixpkgs: pinned jar from Maven Central, only used to run tests
      junitVersion = "6.1.3";
      junit = pkgs.fetchurl {
        url = "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${junitVersion}/junit-platform-console-standalone-${junitVersion}.jar";
        hash = "sha256-5iuWrEddvN6FmeqQXQiPZdkHePhuJZuFakn6XE6iVuw=";
      };

      # inputsFrom only shares packages, so both shells set these
      env = {
        JAVA_HOME = jdk.home;
        JUNIT_JAR = junit;
      };

      # nix develop .#ci: what CI needs to build, lint and test
      ci = pkgs.mkShell (env // {
        packages = [ jdk pkgs.google-java-format ];
      });
    in
    {
      devShells.${system} = {
        inherit ci;

        # nix develop: everything in ci, plus tools for local development
        default = pkgs.mkShell (env // {
          inputsFrom = [ ci ];
          packages = [ pkgs.gh ];

          shellHook = ''
            dim=$'\e[2m'; reset=$'\e[0m'

            echo ""
            echo "  🏰 temanahuiani ''${dim}· dev shell''${reset}"
            echo "  ''${dim}─────────────────────────────''${reset}"
            echo "  ☕ JDK  ${jdk.version}"
            echo "  🐙 gh   ${pkgs.gh.version}"
            echo "  🧹 gjf  ${pkgs.google-java-format.version}"
            echo "  🧪 JUnit ${junitVersion}"
            echo ""
            echo "  ''${dim}cd bash && ./init.sh bplay''${reset}"
            echo ""
          '';
        });
      };
    };
}
