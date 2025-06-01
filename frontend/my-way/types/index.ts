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

export type UserLogin = {
  email: string;
  password: string;
};

export type RoadmapType = {
  id: number;
  mainGoal: string;
  description: string;
  status: string;
  createdAt: string;
  goals: GoalType[];
}

export type GoalType = {
  id: number;
  name: string;
  description: string;
  status: string;
  createdAt: string;
  roadmapIndex: number;
  studyTopics: StudyTopicType[];
}

export type StudyTopicType = {
  id: number;
  name: string;
  description: string;
  status: string;
  createdAt: string;
}