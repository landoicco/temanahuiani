{
  description = "Temanahuiani - a tower defense engine and demo game in vanilla Java";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = { nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
      jdk = pkgs.jdk;
    in
    {
      # nix develop
      devShells.${system}.default = pkgs.mkShell {
        packages = [ jdk pkgs.gh ];

        JAVA_HOME = jdk.home;

        shellHook = ''
          dim=$'\e[2m'; reset=$'\e[0m'

          echo ""
          echo "  🏰 temanahuiani ''${dim}· dev shell''${reset}"
          echo "  ''${dim}─────────────────────────────''${reset}"
          echo "  ☕ JDK  ${jdk.version}"
          echo "  🐙 gh   ${pkgs.gh.version}"
          echo ""
          echo "  ''${dim}cd bash && ./init.sh bplay''${reset}"
          echo ""
        '';
      };
    };
}
