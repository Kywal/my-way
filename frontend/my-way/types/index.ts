import { SVGProps } from "react";

export type IconSvgProps = SVGProps<SVGSVGElement> & {
  size?: number;
};

export type UserRegister = {
  email: string;
  password: string;
  person: Person;
};

type Person = {
  name: string;
};
