package org.andriodtown.tetris;

import android.graphics.Color;

/**
 * Created by user on 2017-09-29.
 */

public class Blocks {

    int colors[] = { Color.RED, Color.BLACK, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.GRAY };

    int blocks[][][][] =
            {
                    {
                            {
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0}
                            },
                            {
                                    {1, 1, 1, 1},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 2, 0, 0},
                                    {2, 2, 2, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 2, 0, 0},
                                    {0, 2, 2, 0},
                                    {0, 2, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 0, 0, 0},
                                    {2, 2, 2, 0},
                                    {0, 2, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 2, 0, 0},
                                    {2, 2, 0, 0},
                                    {0, 2, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 3, 3, 0},
                                    {0, 3, 3, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 4, 0, 0},
                                    {4, 4, 0, 0},
                                    {4, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {4, 4, 0, 0},
                                    {0, 4, 4, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {5, 0, 0, 0},
                                    {5, 5, 0, 0},
                                    {0, 5, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 5, 5, 0},
                                    {5, 5, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 6, 0, 0},
                                    {0, 6, 6, 6},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 6, 6, 0},
                                    {0, 6, 0, 0},
                                    {0, 6, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 6, 6, 6},
                                    {0, 0, 0, 6},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 6, 0},
                                    {0, 0, 6, 0},
                                    {0, 6, 6, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 0, 7, 0},
                                    {7, 7, 7, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 7, 0, 0},
                                    {0, 7, 0, 0},
                                    {0, 7, 7, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {7, 7, 7, 0},
                                    {7, 0, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 7, 7, 0},
                                    {0, 0, 7, 0},
                                    {0, 0, 7, 0},
                                    {0, 0, 0, 0},
                            }
                    }
            };
}