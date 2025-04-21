import { Link } from "@heroui/link";
import { Snippet } from "@heroui/snippet";
import { Code } from "@heroui/code";
import { button as buttonStyles } from "@heroui/theme";

import { siteConfig } from "@/config/site";
import { title, subtitle } from "@/components/primitives";
import { GithubIcon } from "@/components/icons";
import NextLink from "next/link";

import clsx from "clsx";

export default function Home() {
  return (
    <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
      <div className="inline-block max-w-xl text-center justify-center">
        <span className={clsx(title(), "dark:text-white")}>Crie&nbsp;</span>
        <span className={title({ color: "green" })}>Roadmaps&nbsp;</span>
        <br />
        <span className={clsx(title(), "dark:text-white")}>
          para sua construção profissional.
        </span>
        <div className={subtitle({ class: "mt-4" })}>
          Estude, e então faça entrevistas e análises de currículos
        </div>
      </div>

      <div className="flex gap-3">
        <Link
          isExternal
          className={buttonStyles({
            color: "primary",
            radius: "full",
            variant: "shadow",
          })}
          href={siteConfig.links.docs}
        >
          Documentação
        </Link>
        <Link
          isExternal
          className={buttonStyles({ variant: "bordered", radius: "full" })}
          href={siteConfig.links.github}
        >
          <GithubIcon size={20} />
          GitHub
        </Link>
      </div>

      <div className="mt-8">
        <Snippet hideCopyButton hideSymbol variant="bordered">
          <span>
            Inicie agora: {" "}
            <Code color="primary">
              <NextLink href={"/register"}>Cadastre-se</NextLink>
            </Code>
          </span>
        </Snippet>
      </div>
    </section>
  );
}
